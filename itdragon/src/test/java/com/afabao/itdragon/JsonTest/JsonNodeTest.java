package com.afabao.itdragon.JsonTest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

public class JsonNodeTest {

    @Test
    public void json(){
        String jsonData = "{\n" +
                "    \"transportModifyDto\": {\n" +
                "        \"tranContSourceId\": \"b41cdc8e-a297-4f00-af9e-98767c4672f2\",\n" +
                "        \"tranOrderContainersId\": \"b41cdc8e-a297-4f00-af9e-98767c4672f2\",\n" +
                "        \"contNo\": \"TEST666\",\n" +
                "        \"contSealNo\": null,\n" +
                "        \"tranNo\": \"20190723228778\",\n" +
                "        \"tranNodeId\": \"04e7c7d2-4640-41a6-8b33-338cc9f9f57b\",\n" +
                "        \"tranDriversId\": \"c0895994-a599-4232-9efe-21a3290734f9\",\n" +
                "        \"tranDriversName\": \"张三\",\n" +
                "        \"tranTrailerId\": \"96132b1e-7f08-449e-bc28-ea865bc3c7b6\",\n" +
                "        \"tranTrailerNo\": \"闽H23445\",\n" +
                "        \"tranVehiclsId\": null,\n" +
                "        \"tranVehiclsNo\": null,\n" +
                "        \"tranVehiclsCode\": null,\n" +
                "        \"tranRemark\": null,\n" +
                "        \"tranId\": \"\",\n" +
                "        \"tranParentId\": \"63a25f32-fb69-436c-930c-17f15008fa92\",\n" +
                "        \"tranSectionStep\": \"50,61,62\",\n" +
                "        \"tranSection\": 0,\n" +
                "        \"tranBalanceType\": \"\",\n" +
                "        \"tranDropAndPull\": 1,\n" +
                "        \"contWeigh\": 1,\n" +
                "        \"tranOutsourceCompanyId\": null,\n" +
                "        \"tranOutsourceCompanyName\": null,\n" +
                "        \"tranCargoOwnerName\": \"厦门能力科技有限公司\",\n" +
                "        \"tranCargoOwnerId\": \"0c9c9e41-1e1c-470b-b494-481b6d55e8f2\",\n" +
                "        \"tranConsignorId\": \"96336234-d729-41ea-bd03-26f78431ebea\",\n" +
                "        \"tranConsignorCompName\": \"厦门能力科技有限公司\",\n" +
                "        \"tranStatus\": \"8\",\n" +
                "        \"tranDoubleBracket\": \"1\",\n" +
                "        \"tranContRelId\": \"\",\n" +
                "        \"tranPickUp\": \"1\"\n" +
                "    }\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode;

        {
            try {
                jsonNode = mapper.readTree(jsonData);
                JsonNode jsonNode1 = jsonNode.get("transportModifyDto");
                System.out.println(jsonNode1.get("tranContSourceId").isTextual());
                System.out.println(jsonNode1.get("tranContSourceId").asText());
                //mapper.readValue(jsonNode1.traverse(),Class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
