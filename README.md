<p align="left">
  <a href="https://orquesta.dev" target="_blank">
    <img src="https://static.wixstatic.com/media/e063e5_4f60988535a643218a02ad84cf60b7cd~mv2.png/v1/fill/w_130,h_108,al_c,q_85,usm_0.66_1.00_0.01,enc_auto/Logo%2001.png" alt="Orquesta"  height="84">
  </a>
</p>

# Orquesta Java SDK

## Installation

You can find all versions of the Orquesta Java SDK on https://s01.oss.sonatype.org/#nexus-search;quick~orquesta

Add the dependency to your `pom.xml` file

```xml

<dependency>
    <groupId>dev.orquesta</groupId>
    <artifactId>orquesta</artifactId>
    <version>1.0.4</version>
</dependency>
```

## Usage

_You can get your workspace API key from the settings section in your workspace._

## Query a rule

```java
import org.json.JSONObject;
import dev.orquesta.domain.OrquestaOptions;

public class MyProjectClass {
    public static void main(String[] args) {
        // Create Orquesta SDK client
        OrquestaOptions options = new OrquestaOptions("my_workspace_api_key");
        OrquestaClient client = new OrquestaClient(options);

        // Create your query context
        JSONObject ctx = new JSONObject();
        ctx.put("environments", "develop");

        // Query your rule
        String value = client.query("my_rule_key", 'my_default_string_value', ctx, true);
    }
}
```

Note
The above example is for querying a rule of type String. For other type please use the follow implementations:

```java
// Query a rule of type number

import org.json.JSONObject;

public class MyProjectClass {
    public static void main(String[] args) {
        // ...
        Number value = client.query("my_rule_key", 0, ctx, true);
        // ...
    }
}

// Query a rule of type boolean

public class MyProjectClass {
    public static void main(String[] args) {
        // ...
        Boolean value = client.query("my_rule_key", false, ctx, true);
        // ...
    }
}

// Query a rule of type JSON

public class MyProjectClass {
    public static void main(String[] args) {
        // ...
        JSONObject defaultJson = new JSONObject();
        JSONObject value = client.query("my_rule_key", defaultJson, ctx, true);
        // ...
    }
}

// Query a rule of type List

public class MyProjectClass {
    public static void main(String[] args) {
        // ...
        List<String> defaultListValue = new ArrayList<String>();
        List<String> value = client.query("my_rule_key", defaultListValue, ctx, true);
        // ...
    }
}

```

## Query a domain

```java
import org.json.JSONObject;
import dev.orquesta.domain.OrquestaOptions;

public class MyProjectClass {
    public static void main(String[] args) {
        // Create Orquesta SDK client
        OrquestaOptions options = new OrquestaOptions("my_workspace_api_key");
        OrquestaClient client = new OrquestaClient(options);

        // Create your query context
        JSONObject ctx = new JSONObject();
        ctx.put("environments", "develop");

        // Query your rule
        JSONObject domainValue = client.queryDomain("my_rule_key", ctx, true);
    }
}
```