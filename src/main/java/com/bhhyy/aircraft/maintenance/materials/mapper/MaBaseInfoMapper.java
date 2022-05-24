package com.bhhyy.aircraft.maintenance.materials.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bhhyy.aircraft.maintenance.materials.bean.MaBaseInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Jinglin
 * @since 2022-04-25
 */
@Mapper
public interface MaBaseInfoMapper extends BaseMapper<MaBaseInfo> {

}
