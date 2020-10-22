package check;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//该注解会保留到class字节码文件中
@Target(ElementType.METHOD)//该注解能够作用与方法上
public @interface Check {
}
