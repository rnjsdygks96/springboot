package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class HellobootApplication {

    public static void main(String[] args) {

        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                WebServer webserver = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet",
                            new DispatcherServlet(this)
                    ).addMapping("/*");
                });
                webserver.start();
            }
        };
                // get 으로 돌려줌.
            applicationContext.registerBean(HelloController.class);
            applicationContext.registerBean(SimpleHelloService.class);
            applicationContext.refresh();

        };






       /*
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        // get 으로 돌려줌.
        applicationContext.registerBean(HelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh();

        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webserver = serverFactory.getWebServer(servletContext -> {
           // HelloController helloController = new HelloController();

            servletContext.addServlet("frontcontroller", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    // 인증, 보안, 다국어, 공통기능   맵핑
                    if(req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
                        String name = req.getParameter("name");

                        // 스프링 컨테이너에서 가져와서 사용한다는 뜻                      여기는 클래스에 메소드가 하나일경우.
                        HelloController helloController = applicationContext.getBean(HelloController.class);
                        String ret = helloController.hello(name); // 새로운 타입(String) 으로 변환해주는게 바인딩이라고 함.

                        resp.setContentType(*//*HttpHeaders.CONTENT_TYPE,*//* MediaType.TEXT_PLAIN_VALUE);
                        resp.getWriter().println(ret);
                    } else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value()); // 404 던져줌.
                    }
                }
            }).addMapping("/*");
        });
        webserver.start();*/

}
