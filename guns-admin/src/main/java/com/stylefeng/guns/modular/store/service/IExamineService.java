package com.stylefeng.guns.modular.store.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.store.vo.ExamineVO;
import com.stylefeng.guns.modular.system.model.Examine;

/**
 * <p>
 * 检查项信息 服务类
 * </p>
 *
 * @author jackial
 * @since 2018-08-29
 */
public interface IExamineService extends IService<Examine> {

    List<ExamineVO> selectList();
}
