import com.alibaba.fastjson2.JSON;
import com.ruoyi.RuoYiApplication;
import com.ruoyi.framework.web.domain.server.Sys;
import com.ruoyi.lab.domain.AiFace;
import com.ruoyi.lab.service.IAiFaceRecService;
import com.ruoyi.lab.service.IAiFaceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = RuoYiApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class FaceTest {

    @Autowired
    private IAiFaceService iAiFaceService;

    @Autowired
    private IAiFaceRecService iAiFaceRecService;

    @Test
    public void clearFace() {
        System.out.println("开始删除");
        iAiFaceRecService.clearFace();
        System.out.println("删除结束");
    }

    @Test
    public void insertFace() {
        System.out.println("insertFace");
        log.debug("insert face");
        List<AiFace> aiFaceList = iAiFaceService.selectAiFaceList(new AiFace());
        log.debug(JSON.toJSONString(aiFaceList));

        for (AiFace aiFace : aiFaceList) {
            iAiFaceService.updateAiFace(aiFace);
            log.debug("更新成功：{}{}",aiFace.getFaceId(),JSON.toJSONString(aiFace));
        }
    }

    @Test
    public void testDetectExpression() {
        System.out.println("testDetectExpression");
        iAiFaceRecService.detectExpression("/home/zhangqinxiong/图片/合照01.jpg");
    }
}
