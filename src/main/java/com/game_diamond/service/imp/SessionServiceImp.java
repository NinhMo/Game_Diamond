package com.game_diamond.service.imp;

import com.game_diamond.entities.Session;
import com.game_diamond.repository.SessionRepo;
import com.game_diamond.service.SessionService;
import com.game_diamond.utils.TimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SessionServiceImp implements SessionService {

    private SessionRepo sessionRepo;

    @Override
    public Session create(Long userId) {
        Session session = new Session();
        session.setId(UUID.randomUUID().toString());
        session.setUserId(userId);
        session.setBeginTime(TimeUtils.getCurrentTimestamp());
        return sessionRepo.save(session);
    }


    @Override
    public Session close(String sessionId) {
        Optional<Session> sessionOp = sessionRepo.findById(sessionId);
        if(!sessionOp.isPresent()){
            return null;
        }
        sessionOp.get().setEndTime(TimeUtils.getCurrentTimestamp());
        return sessionRepo.save(sessionOp.get());
    }
}
