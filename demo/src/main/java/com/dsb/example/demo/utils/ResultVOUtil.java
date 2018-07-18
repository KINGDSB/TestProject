package com.dsb.example.demo.utils;

/**
 * Created by Coder.One
 * 2018/4/30 14:14
 */
public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMessage("成功");
        return resultVO;
    }

    public static ResultVO error(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(500);
        resultVO.setMessage(object.toString());
        return resultVO;
    }
    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(msg);
        return resultVO;
    }

//    public static ResultVO success(Page page) {
//        ResultVO resultVO = new ResultVO();
//        Page pageVO = new Page();
//        BeanUtils.copyProperties(page, pageVO);
//        resultVO.setData(pageVO);
//        resultVO.setCode(0);
//        resultVO.setMessage("成功");
//        return resultVO;
//    }

}
