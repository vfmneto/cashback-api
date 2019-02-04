package br.com.vfmneto.cashbackapi.init;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public interface InicializadorCatalogoDiscoListener extends ApplicationListener<ContextRefreshedEvent> {
}
