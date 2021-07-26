package generate;

import generate.ReportInfo;

public interface ReportInfoDao {
    int insert(ReportInfo record);

    int insertSelective(ReportInfo record);
}