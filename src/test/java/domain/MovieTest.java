package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void 영화의_러닝타임은_음수이면_예외를_던진다(){
        assertThatIllegalArgumentException().isThrownBy(()->
                new Movie(-1, "title"));
    }

    @Test
    void 영화의_러닝타임이_양수이면_예외를_던지지_않는다(){
        assertThatNoException().isThrownBy(()->
                new Movie(1, "title"));
    }

    @Test
    void 영화_제목이_공백이면_예외를_던진다(){
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Movie(1, ""));
    }

}