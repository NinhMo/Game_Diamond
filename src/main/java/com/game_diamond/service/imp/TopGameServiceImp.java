package com.game_diamond.service.imp;

import com.game_diamond.entities.TopGameMonth;
import com.game_diamond.entities.TopGameWeek;
import com.game_diamond.payload.projection.TopGameMonthProj;
import com.game_diamond.payload.projection.TopGameProj;
import com.game_diamond.repository.TopGameMonthRepo;
import com.game_diamond.repository.TopGameWeekRepo;
import com.game_diamond.service.TopGameService;
import com.game_diamond.utils.TimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TopGameServiceImp implements TopGameService {
    private TopGameWeekRepo topGameWeekRepo;
    private TopGameMonthRepo topGameMonthRepo;

    @Override
    public Page<TopGameProj> topGameByWeek(String weekCode, int page, int size) {
        return topGameWeekRepo.getTopGameByWeek(weekCode, PageRequest.of(page, size));
    }
    @Override
    public Page<TopGameMonthProj> topGameByMonth(String monthCode, int page, int size) {
        return topGameMonthRepo.getTopGameByMonth(monthCode, PageRequest.of(page, size));
    }

    @Override
    public void createOrUpdateTopWeekByWeekCode(Long userId, Long updateDiamond, String weekCode) {
        Optional<TopGameWeek> topGameWeekOp = topGameWeekRepo.findByUserIdAndWeekCode(userId, weekCode);
        if(topGameWeekOp.isPresent()){
            topGameWeekOp.get().setTotalDiamond(topGameWeekOp.get().getTotalDiamond() + updateDiamond);
            topGameWeekOp.get().setUpdatesAt(TimeUtils.getCurrentTimestamp());
            topGameWeekRepo.save(topGameWeekOp.get());
            return;
        }
        TopGameWeek topGameWeek = new TopGameWeek();
        topGameWeek.setUserId(userId);
        topGameWeek.setTotalDiamond(updateDiamond);
        topGameWeek.setWeekCode(weekCode);
        topGameWeekRepo.save(topGameWeek);
    }

    @Override
    public void createOrUpdateTopMonthByMonthCode(Long userId, Long updateDiamond, String monthCode) {
        Optional<TopGameMonth> topGameMonthOp = topGameMonthRepo.findByUserIdAndMonthCode(userId, monthCode);
        if(topGameMonthOp.isPresent()){
            topGameMonthOp.get().setTotalDiamond(topGameMonthOp.get().getTotalDiamond() + updateDiamond);
            topGameMonthOp.get().setUpdatesAt(TimeUtils.getCurrentTimestamp());
            topGameMonthRepo.save(topGameMonthOp.get());
            return;
        }
        TopGameMonth topGameMonth = new TopGameMonth();
        topGameMonth.setUserId(userId);
        topGameMonth.setTotalDiamond(updateDiamond);
        topGameMonth.setMonthCode(monthCode);
        topGameMonthRepo.save(topGameMonth);
    }
}
