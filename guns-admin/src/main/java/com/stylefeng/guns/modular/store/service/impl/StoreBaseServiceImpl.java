package com.stylefeng.guns.modular.store.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.store.service.IStoreBaseService;
import com.stylefeng.guns.modular.system.dao.StoreBaseMapper;
import com.stylefeng.guns.modular.system.dao.vo.StoreListVO;
import com.stylefeng.guns.modular.system.dao.vo.StoreQueryVO;
import com.stylefeng.guns.modular.system.model.StoreBase;

/**
 * <p>
 * 店铺 服务实现类
 * </p>
 *
 * @author jackial
 * @since 2018-08-27
 */
@Service
@Transactional
public class StoreBaseServiceImpl extends ServiceImpl<StoreBaseMapper, StoreBase> implements IStoreBaseService {

    @Override
    @Transactional(readOnly = true)
    public Page<StoreListVO> selectStores(StoreQueryVO vo, Page<StoreListVO> page) {
        return page.setRecords(this.baseMapper.selectStores(vo, page));
    }

}
