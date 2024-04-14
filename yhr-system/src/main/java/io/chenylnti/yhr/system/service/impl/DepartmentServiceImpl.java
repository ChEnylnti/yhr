package io.chenylnti.yhr.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.chenylnti.yhr.system.entity.Department;
import io.chenylnti.yhr.system.entity.vo.DepartmentWithChildren;
import io.chenylnti.yhr.system.mapper.DepartmentMapper;
import io.chenylnti.yhr.system.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenylnti
 * @since 2024-04-10
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartmentsByParentId(-1);
    }


    @Override
    public boolean addDepartment(Department department) {
        department.setEnabled(true);
        //先查找父亲信息
        Department parentDepartment = baseMapper.selectById(department.getParentId());
        System.out.println("pd1"+parentDepartment);
        //有父亲，则将父亲的isParent变为true
        parentDepartment.setIsParent(true);
        //将父亲信息的is_parent改为1
        LambdaUpdateWrapper<Department> eq = Wrappers.lambdaUpdate(Department.class).eq(Department::getId, parentDepartment.getId());
        baseMapper.update(parentDepartment,eq);
        save(department);
        //获取自增id
        Integer id = department.getId();
        //将parent的DepPath与自己的id进行拼接就是自己的DepPath了
        department.setDepPath(parentDepartment.getDepPath()+"."+ id);
        LambdaUpdateWrapper<Department> eq2 = Wrappers.lambdaUpdate(Department.class).eq(Department::getId, department.getId());
        baseMapper.update(department, eq2);
        return id != null;
    }

    @Override
    public boolean deleteDepartment(Integer id) {
        boolean result = true;
        Department department = baseMapper.selectById(id);
        if (department.getIsParent()){
            return false;
        }
        try {
            baseMapper.deleteById(id);
        } catch (Exception e) {
            result = false;
        }
        //如果删除成功，则查找该父类下还有没有children，若没有children则把父类的isParent改为false
        if (result){
            Department parentDepartment = baseMapper.selectById(department.getParentId());
            List<Department> thisParentChildren = departmentMapper.getAllDepartmentsByParentId(parentDepartment.getId());
            //如果是空的就把isParent改为false
            if (thisParentChildren.isEmpty()){
                parentDepartment.setIsParent(false);
                baseMapper.update(parentDepartment,Wrappers.<Department>lambdaUpdate().eq(Department::getId, parentDepartment.getId()));
            }
        }
        return result;
    }
}
