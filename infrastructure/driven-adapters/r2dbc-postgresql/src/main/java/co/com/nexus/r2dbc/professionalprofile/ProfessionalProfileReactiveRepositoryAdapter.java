package co.com.nexus.r2dbc.professionalprofile;

import co.com.nexus.model.professionalprofile.ProfessionalProfileModel;
import co.com.nexus.model.professionalprofile.gateways.ProfessionalProfileRepository;
import co.com.nexus.model.shared.constants.HttpStatus;
import co.com.nexus.model.shared.exception.NexusException;
import co.com.nexus.model.shared.pagination.PagingResult;
import co.com.nexus.model.shared.pagination.QueryParams;
import co.com.nexus.model.user.UserModel;
import co.com.nexus.model.user.gateways.UserRepository;
import co.com.nexus.r2dbc.helper.ReactiveAdapterOperations;
import co.com.nexus.r2dbc.professionalprofile.mapper.ProfessionalProfileMapper;
import co.com.nexus.r2dbc.user.User;
import co.com.nexus.r2dbc.user.mapper.UserMapper;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class ProfessionalProfileReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        ProfessionalProfileModel,
        ProfessionalProfile,
        UUID,
        ProfessionalProfileReactiveRepository
        > implements ProfessionalProfileRepository {

    public ProfessionalProfileReactiveRepositoryAdapter(ProfessionalProfileReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, ProfessionalProfileModel.ProfessionalProfileModelBuilder.class).build());
    }


    @Override
    public Mono<ProfessionalProfileModel> findByUserId(UUID userId) {
        return this.repository.findByUserId(userId)
                .switchIfEmpty(Mono.error(new NexusException("PERFIL PROFESIONAL NO ENCONTRADO" , HttpStatus.NOT_FOUND)))
                .map(ProfessionalProfileMapper::mapToModel);
    }
}
