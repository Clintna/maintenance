package com.bhhyy.aircraft.maintenance.staff.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bhhyy.aircraft.maintenance.staff.bean.StDeptInfo;
import com.bhhyy.aircraft.maintenance.staff.entity.DeptTree;
import com.bhhyy.aircraft.maintenance.staff.mapper.StDeptInfoMapper;
import com.bhhyy.aircraft.maintenance.staff.service.StDeptInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhhyy.aircraft.maintenance.staff.utils.TreeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jinglin
 * @since 2022-05-23
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class StDeptInfoServiceImpl extends ServiceImpl<StDeptInfoMapper, StDeptInfo> implements StDeptInfoService {

    @Override
    public void createDept(StDeptInfo stDeptInfo) {
        stDeptInfo.setCreateTime(LocalDateTime.now());
        stDeptInfo.setModifyTime(LocalDateTime.now());
        if (null == stDeptInfo.getParentId()){
            stDeptInfo.setParentId(StDeptInfo.TOP_NODE);
        }
        save(stDeptInfo);
    }

    @Override
    public List<DeptTree<StDeptInfo>> findDeptTree() {
        List<StDeptInfo> deptList = baseMapper.selectList(new QueryWrapper<>());
        List<DeptTree<StDeptInfo>> trees = convertDept(deptList);
        return TreeUtil.buildDeptTree(trees);
    }


    private List<DeptTree<StDeptInfo>> convertDept(List<StDeptInfo> Dept) {
        List<DeptTree<StDeptInfo>> trees = new ArrayList<>();
        Dept.forEach(dept -> {
            DeptTree<StDeptInfo> tree = new DeptTree<>();
            tree.setId(String.valueOf(dept.getDeptId()));
            tree.setParentId(String.valueOf(dept.getParentId()));
            tree.setName(dept.getDeptName());
            tree.setData(dept);
            trees.add(tree);
        });
        return trees;
    }
}
