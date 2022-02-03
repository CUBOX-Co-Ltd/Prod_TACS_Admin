package cubox.admin.main.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import cubox.admin.main.service.vo.AuthGroupVO;
import cubox.admin.main.service.vo.CardInfoVO;
import cubox.admin.main.service.vo.ExcelVO;
import cubox.admin.main.service.vo.GateVO;
import cubox.admin.main.service.vo.LoginVO;
import cubox.admin.main.service.vo.UserInfoVO;


@Repository("stystemInfoDAO")
public class SystemInfoDAO extends EgovAbstractMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(SystemInfoDAO.class);
	
	private static final String sqlNameSpace = "stystemInfo.";




}
