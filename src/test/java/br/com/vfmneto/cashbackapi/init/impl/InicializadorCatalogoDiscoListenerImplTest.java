package br.com.vfmneto.cashbackapi.init.impl;

import br.com.vfmneto.cashbackapi.service.InicializadorCatalogoDiscoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class InicializadorCatalogoDiscoListenerImplTest {

    private InicializadorCatalogoDiscoListenerImpl inicializadorCatalogoDiscoListener;

    @Mock private InicializadorCatalogoDiscoService service;

    @Before
    public void inicializar() {
        inicializadorCatalogoDiscoListener = new InicializadorCatalogoDiscoListenerImpl(service);
    }

    @Test
    public void aoInicializarAplicacaoDeveriaInicializarCatalogoDeDiscos() {

        inicializadorCatalogoDiscoListener.onApplicationEvent(null);

        verify(service).inicializar();
    }

}