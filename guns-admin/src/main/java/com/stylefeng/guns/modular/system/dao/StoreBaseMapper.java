package com.stylefeng.guns.modular.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.dao.vo.StoreListVO;
import com.stylefeng.guns.modular.system.dao.vo.StoreQueryVO;
import com.stylefeng.guns.modular.system.model.StoreBase;

/**
 * <p>
 * 店铺 Mapper 接口
 * </p>
 *
 * @author jackial
 * @since 2018-08-27
 */
public interface StoreBaseMapper extends BaseMapper<StoreBase> {

    List<StoreListVO> selectStores(StoreQueryVO vo, @Param("page") Page<StoreListVO> page);
}
