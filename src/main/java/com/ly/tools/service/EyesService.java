package com.ly.tools.service;

import com.ly.tools.entity.dto.CompanyDTO;
import com.ly.tools.entity.dto.TitleMesDTO;
import com.ly.tools.entity.vo.BiddingInfoVO;

import java.util.List;

public interface EyesService {

    List<BiddingInfoVO> getEyesCompanyMes(CompanyDTO dto);

    void getEyesTitleMes(TitleMesDTO titleMesDTO);
}
