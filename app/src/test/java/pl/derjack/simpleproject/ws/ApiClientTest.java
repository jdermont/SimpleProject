package pl.derjack.simpleproject.ws;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.List;

import io.reactivex.Observable;
import pl.derjack.simpleproject.ModelUtilsKt;
import pl.derjack.simpleproject.RxJavaSchedulersOverrideRule;
import pl.derjack.simpleproject.ws.model.WsTVShow;

@RunWith(MockitoJUnitRunner.class)
public class ApiClientTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public RxJavaSchedulersOverrideRule rxRule = new RxJavaSchedulersOverrideRule();

    @Mock
    ApiClient apiClient;

    @Test
    public void testRetrieveTvShows() {
        Mockito.when(apiClient.retrieveTVShows(Mockito.anyString()))
                .thenReturn(Observable.just(ModelUtilsKt.createShowList(1)));

        Observable<List<WsTVShow>> showsObservable = apiClient.retrieveTVShows("xxx");
        List<WsTVShow> shows = showsObservable.blockingFirst();

        Mockito.verify(apiClient).retrieveTVShows("xxx");

        Assert.assertEquals(1, shows.size());
    }


}
