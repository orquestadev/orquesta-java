package dev.orquesta.client;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import dev.orquesta.domain.OrquestaOptions;

public class Test {

    public static void main(String[] args) {


Ï

        System.out.println(5 == 5.35);

        Number number = new Float(0f);

        OrquestaOptions options = new OrquestaOptions(
                "RQST.8d26c87d55c54343abb4c4a01b1a45a1.RGbKii_0asL0N1LXzeFGYjMMhYu0qsFoBTQwcpdRjq0", 100);
        OrquestaClient client = new OrquestaClient(options);
        try {
            JSONObject ctx = new JSONObject();
            ctx.put("environments", "develop");
            ctx.put("environments1", "develop");
            ctx.put("environments2", "develop");

            Number nmb = client.query("murtaza_example_number", 0, ctx, true);
            System.out.println("Float " + nmb.floatValue());
            System.out.println("Double " + nmb.doubleValue());
            System.out.println("Integer " + nmb.intValue());

            String query = client.query("security-benefits-banner-source", "hey how are you", ctx, true);
            System.out.println(query);
            JSONObject queryDomain = client.queryDomain("default", ctx, true);
            System.out.println(queryDomain);
            List<String> list = new ArrayList<String>();
            list.add("Test");
            List<String> query2 = client.query("security-benefits-retirement-menu", list, ctx, true);
            System.out.println(query2);



            String query3 = client.query("security-benefits-banner-source", "hey how are you", ctx, true);
            System.out.println(query3);
            System.out.println(client.queryDomain("default", null, ctx, true));
            System.out.println(client.query("security-benefits-retirement-menu", list, ctx, true));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
