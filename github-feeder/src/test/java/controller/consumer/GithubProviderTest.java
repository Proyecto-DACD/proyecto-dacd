package controller.consumer;

import com.arthead.controller.consume.GithubConnection;
import com.arthead.controller.consume.GithubDeserializer;
import com.arthead.controller.consume.GithubFetcher;
import com.arthead.controller.consume.GithubProvider;
import com.arthead.model.GithubData;
import com.arthead.model.Information;
import com.arthead.model.Repository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Map;

public class GithubProviderTest {
    private GithubProvider provider;
    private final Map<String, String> repoQuery = Map.of(
            "owner", "octocat",
            "repo", "Hello-World"
    );

    @Before
    public void setUp() {
        String apiKey = "API_KEY";
        String baseUrl = "https://api.github.com/repos/";

        GithubConnection connection = new GithubConnection(apiKey, baseUrl);
        GithubFetcher fetcher = new GithubFetcher();
        GithubDeserializer deserializer = new GithubDeserializer();

        provider = new GithubProvider(connection, fetcher, deserializer);
    }

    @Test
    public void provide_ShouldReturnValidGithubData() {
        GithubData data = provider.provide(repoQuery);

        Assert.assertNotNull(data);
        Repository repo = data.getRepository();
        Assert.assertNotNull(repo);
        Assert.assertEquals("Hello-World", repo.getName());
        Assert.assertEquals("octocat", repo.getOwner());

        Information info = data.getInformation();
        Assert.assertNotNull(info);
        Assert.assertTrue(info.getStars() >= 0);
        Assert.assertTrue(info.getForks() >= 0);
        Assert.assertTrue(info.getIssuesAndPullRequest() >= 0);
        Assert.assertTrue(info.getWatchers() >= 0);
    }
}
