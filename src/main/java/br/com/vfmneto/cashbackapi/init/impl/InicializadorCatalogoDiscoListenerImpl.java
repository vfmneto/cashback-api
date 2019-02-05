package br.com.vfmneto.cashbackapi.init.impl;

import br.com.vfmneto.cashbackapi.init.InicializadorCatalogoDiscoListener;
import br.com.vfmneto.cashbackapi.service.InicializadorCatalogoDiscoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class InicializadorCatalogoDiscoListenerImpl implements InicializadorCatalogoDiscoListener {

    public static final Logger LOGGER = Logger.getLogger(InicializadorCatalogoDiscoListenerImpl.class.getName());

    private InicializadorCatalogoDiscoService inicializadorCatalogoDiscoService;

    @Autowired
    public InicializadorCatalogoDiscoListenerImpl(InicializadorCatalogoDiscoService inicializadorCatalogoDiscoService) {
        this.inicializadorCatalogoDiscoService = inicializadorCatalogoDiscoService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        LOGGER.info("iniciando carregamento do catálogo de discos com API do Spotify");

        inicializadorCatalogoDiscoService.inicializar();

        LOGGER.info("fim carregamento do catálogo de discos com API do Spotify");
    }
}
