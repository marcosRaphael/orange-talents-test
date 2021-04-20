package util;

import br.com.zup.orangetalents.domain.User;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

public class UserCreator {

    public static User createValidUser() {
        return new User(1L, "Marcos", "marcos@hotmail.com", "04336170045"
                , LocalDate.of(1998, 5, 9));
    }
}
