package co.edu.udea.os.ahorcado.config;

import co.edu.udea.os.ahorcado.service.config.IFirstRunAppConfiguration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
public class FirstRunAhorcadoServlet implements ServletContextListener {

    @Override()
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println(" + Creating the context servlet application...");

        ApplicationContext appContext =
                WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        IFirstRunAppConfiguration initialConfig =
                (IFirstRunAppConfiguration) appContext.getBean("firstRunAppConfiguration");
        initialConfig.createDefaultData();
    }

    @Override()
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println(" + Destroying the context servlet application.");
    }
}