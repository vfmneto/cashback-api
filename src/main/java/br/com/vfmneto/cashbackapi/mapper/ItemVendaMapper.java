package br.com.vfmneto.cashbackapi.mapper;

import br.com.vfmneto.cashbackapi.domain.ItemVenda;
import br.com.vfmneto.cashbackapi.dto.ItemVendaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemVendaMapper extends EntityMapper<ItemVendaDTO, ItemVenda> {

    @Override
    ItemVenda toEntity(ItemVendaDTO dto);

    @Override
    ItemVendaDTO toDto(ItemVenda entity);

    default ItemVenda fromId(Long id) {
        if (id == null) {
            return null;
        }
        ItemVenda itemVenda = new ItemVenda();
        itemVenda.setId(id);
        return itemVenda;
    }
    
    
    
}
