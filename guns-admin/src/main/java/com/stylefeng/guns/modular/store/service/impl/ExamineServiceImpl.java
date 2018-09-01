package com.stylefeng.guns.modular.store.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.constant.EntityConstants;
import com.stylefeng.guns.modular.store.service.IExamineService;
import com.stylefeng.guns.modular.store.vo.ExamineItemVO;
import com.stylefeng.guns.modular.store.vo.ExamineVO;
import com.stylefeng.guns.modular.system.dao.ExamineMapper;
import com.stylefeng.guns.modular.system.model.Examine;

/**
 * <p>
 * 检查项信息 服务实现类
 * </p>
 *
 * @author jackial
 * @since 2018-08-29
 */
@Service
public class ExamineServiceImpl extends ServiceImpl<ExamineMapper, Examine> implements IExamineService {

    @Override
    public List<ExamineVO> selectList() {
        List<ExamineVO> list = new ArrayList<>();
        List<Examine> examines = selectList(null);
        List<Examine> roots = findRoot(examines);
        for (Examine examine : roots) {
            ExamineVO vo = new ExamineVO();
            BeanUtils.copyProperties(examine, vo, EntityConstants.UPDATE_IGNORE_PROPERTIES);
            vo.setList(findChildren(examine.getId(), examines));
            list.add(vo);

        }
        return list;
    }

    private List<Examine> findRoot(List<Examine> examines) {
        List<Examine> roots = new ArrayList<>();
        for (Examine examine : examines) {
            if (null == examine.getParent()) {
                roots.add(examine);
            }
        }
        return roots;
    }

    private List<ExamineItemVO> findChildren(Integer id, List<Examine> examines) {
        List<Examine> children = new ArrayList<>();
        for (Examine examine : examines) {
            if (id.equals(examine.getParent())) {
                children.add(examine);
            }
        }
        List<ExamineItemVO> list = new ArrayList<>();
        for (Examine examine : children) {
            ExamineItemVO vo = new ExamineItemVO();
            BeanUtils.copyProperties(examine, vo, EntityConstants.UPDATE_IGNORE_PROPERTIES);
            list.add(vo);
        }
        return list;
    }
}
