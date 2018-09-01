package com.stylefeng.guns.modular.store.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.dao.vo.StoreListVO;
import com.stylefeng.guns.modular.system.dao.vo.StoreQueryVO;
import com.stylefeng.guns.modular.system.model.StoreBase;

/**
 * <p>
 * 店铺 服务类
 * </p>
 *
 * @author jackial
 * @since 2018-08-27
 */
public interface IStoreBaseService extends IService<StoreBase> {

    Page<StoreListVO> selectStores(StoreQueryVO vo, Page<StoreListVO> page);
}
