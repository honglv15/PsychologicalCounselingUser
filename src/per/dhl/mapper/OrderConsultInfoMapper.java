package per.dhl.mapper;

import org.apache.ibatis.annotations.Param;
import per.dhl.qo.ConsultInfoQo;
import per.dhl.qo.OrderConsultQo;

import java.util.ArrayList;

public interface OrderConsultInfoMapper {
    Integer addOrder(@Param("userId") Integer userId, @Param("fieldId") Integer fieldId, @Param("consultId") Integer consultId, @Param("orderTime") String radioVal, @Param("problemTxt") String problemTxt);

    Integer updateOrder(@Param("orderTime") String radioVal);

    ArrayList<OrderConsultQo> getOrderConsultTable(@Param("userId") Integer userId);


    Integer getConsultId(@Param("adminId") Integer adminId);

    ArrayList<ConsultInfoQo> GetConsultInfo(@Param("adminId") Integer adminId);

    ArrayList<String> getFileds(@Param("consultId") String consultId);

    Integer evaluateConsult(@Param("consultId") Integer consultId, @Param("evaluateTxt") String evaluateTxt);

    Integer Evaluated(Integer consultId);
}
