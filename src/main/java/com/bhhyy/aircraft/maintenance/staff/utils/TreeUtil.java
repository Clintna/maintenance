package com.bhhyy.aircraft.maintenance.staff.utils;

import com.bhhyy.aircraft.maintenance.staff.entity.DeptTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jinglin
 * @Date: 2022/05/23
 * @Description:
 */
public class TreeUtil {

    private static final String TOP_NODE_ID = "0";


    public static <T> List<DeptTree<T>> buildDeptTree(List<DeptTree<T>> nodes) {
        if (nodes == null) {
            return null;
        }
        List<DeptTree<T>> result = new ArrayList<>();
        nodes.forEach(children -> {
            String pid = children.getParentId();
            if (pid == null || TOP_NODE_ID.equals(pid)) {
                result.add(children);
                return;
            }
            for (DeptTree<T> n : nodes) {
                String id = n.getId();
                if (id != null && id.equals(pid)) {
                    if (n.getChildren() == null) {
                        n.initChildren();
                    }
                    n.getChildren().add(children);
                    children.setHasParent(true);
                    n.setHasChild(true);
                    return;
                }
            }
        });

        return result;
    }
}
