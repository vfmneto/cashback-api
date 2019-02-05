package br.com.vfmneto.cashbackapi.mapper;

import br.com.vfmneto.cashbackapi.domain.Venda;
import br.com.vfmneto.cashbackapi.dto.VendaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = { ItemVendaMapper.class })
public interface VendaMapper extends EntityMapper<VendaDTO, Venda> {

    @Override
    Venda toEntity(VendaDTO dto);

    @Override
    VendaDTO toDto(Venda entity);

    default Venda fromId(Long id) {
        if (id == null) {
            return null;
        }
        Venda venda = new Venda();
        venda.setId(id);
        return venda;
    }
    
    
    
}
