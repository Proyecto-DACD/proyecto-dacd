import com.arthead.controller.consume.CoinMarketCapDeserializer;
import com.arthead.model.Coin;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class CoinMarketCapDeserializerTest {
    private String json;

    @Before
    public void setUp() {
        json = "{\n" +
                "  \"data\": {\n" +
                "    \"1\": {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Bitcoin\",\n" +
                "      \"symbol\": \"BTC\",\n" +
                "      \"slug\": \"bitcoin\",\n" +
                "      \"is_active\": 1,\n" +
                "      \"is_fiat\": 0,\n" +
                "      \"circulating_supply\": 17199862,\n" +
                "      \"total_supply\": 17199862,\n" +
                "      \"max_supply\": 21000000,\n" +
                "      \"quote\": {\n" +
                "        \"USD\": {\n" +
                "          \"price\": 6602.60701122,\n" +
                "          \"volume_24h\": 4314444687.5194,\n" +
                "          \"market_cap\": 852164659250.2758\n" +
                "        },\n" +
                "        \"EUR\": {\n" +
                "          \"price\": 6200.75,\n" +
                "          \"volume_24h\": 4000000000.0,\n" +
                "          \"market_cap\": 800000000000.0\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    \"1027\": {\n" +
                "      \"id\": 1027,\n" +
                "      \"name\": \"Ethereum\",\n" +
                "      \"symbol\": \"ETH\",\n" +
                "      \"circulating_supply\": 100000000,\n" +
                "      \"total_supply\": 110000000,\n" +
                "      \"quote\": {\n" +
                "        \"USD\": {\n" +
                "          \"price\": 400.50,\n" +
                "          \"volume_24h\": 2000000000.0,\n" +
                "          \"market_cap\": 50000000000.0\n" +
                "        },\n" +
                "        \"EUR\": {\n" +
                "          \"price\": 380.20,\n" +
                "          \"volume_24h\": 1900000000.0,\n" +
                "          \"market_cap\": 48000000000.0\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";
    }

    @Test
    public void deserializerTest(){
        CoinMarketCapDeserializer deserializer = new CoinMarketCapDeserializer();
        List<Coin> deserializedResponse = deserializer.deserialize(json);

        Coin bitcoin = deserializedResponse.getFirst();
        Assertions.assertEquals("Bitcoin", bitcoin.getName());
        Assertions.assertEquals("BTC", bitcoin.getSymbol());
        Assertions.assertEquals(21000000, bitcoin.getMaxSupply());
        Assertions.assertEquals(17199862, bitcoin.getCirculatingSupply());
        Assertions.assertTrue(bitcoin.getActive());
    }


}

