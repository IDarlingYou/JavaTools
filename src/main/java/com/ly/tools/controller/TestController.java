package com.ly.tools.controller;

import com.ly.tools.entity.dto.CompanyDTO;
import com.ly.tools.entity.dto.TitleMesDTO;
import com.ly.tools.entity.vo.BiddingInfoVO;
import com.ly.tools.service.EyesService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: LY
 * @Date: 2021/3/9 11:34
 * @Description:
 **/
@Slf4j
@RestController
@RequestMapping("/eyesData/")
public class TestController {

    @Autowired
    private EyesService eyesService;

    @ApiOperation("通过公司名称或ID获取企业专利信息")
    @PostMapping("getEyesCompanyMes")
    public List<BiddingInfoVO> getEyesCompanyMes(@RequestBody CompanyDTO dto) {
        return eyesService.getEyesCompanyMes(dto);
    }

    @ApiOperation("通过招投标标题、采购人、公告类型等方式，查询招投标的有关信息")
    @PostMapping("getEyesTitleMes")
    public void getEyesTitleMes(TitleMesDTO titleMesDTO) {

    }


}
