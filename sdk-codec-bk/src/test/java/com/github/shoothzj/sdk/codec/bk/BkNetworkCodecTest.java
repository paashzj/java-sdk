package com.github.shoothzj.sdk.codec.bk;

import com.github.shoothzj.test.base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.bookkeeper.proto.BookkeeperProtocol;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class BkNetworkCodecTest extends BaseTest {

    @Test
    public void testGetBookieInfoResp() {
        final String hexStr = "0a060803100818051000ca0610080010808090ceac0f1880e0c6bdcf03";
        final BookkeeperProtocol.Response response = BkNetworkCodec.decodeRespContent(hexStr);
        final BookkeeperProtocol.BKPacketHeader bkPacketHeader = response.getHeader();
        Assert.assertNotNull(bkPacketHeader);
        final BookkeeperProtocol.GetBookieInfoResponse bookieInfoResponse = response.getGetBookieInfoResponse();
        Assert.assertNotNull(bookieInfoResponse);
        log.info("response is {}", response);
    }

}