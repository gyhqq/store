package com.gyhqq.item.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyhqq.common.Exception.GyhException;
import com.gyhqq.common.enums.ExceptionEnum;
import com.gyhqq.item.service.TbSkuService;
import com.gyhqq.item.entity.TbSku;
import com.gyhqq.item.mapper.TbSkuMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * <p>
 * sku表,该表表示具体的商品实体,如黑色的 64g的iphone 8 服务实现类
 * </p>
 *
 * @author GYHQQ
 * @since 2019-08-12
 */
@Service
public class TbSkuServiceImpl extends ServiceImpl<TbSkuMapper, TbSku> implements TbSkuService {

    @Override
    @Transactional
    public void minusStock(Map<Long, Integer> skuIdNumMap) {
        for (Long skuId : skuIdNumMap.keySet()) {
            Integer num = skuIdNumMap.get(skuId);
            int count = this.getBaseMapper().minusStock(skuId,num);
            if(count != 1){
                throw new GyhException(ExceptionEnum.STOCK_NOT_ENOUGH_ERROR);
            }
        }
    }
}
