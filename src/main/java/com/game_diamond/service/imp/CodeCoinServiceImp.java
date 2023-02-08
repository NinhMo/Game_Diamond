package com.game_diamond.service.imp;

import com.game_diamond.entities.CodeCoin;
import com.game_diamond.entities.UserCoin;
import com.game_diamond.payload.response.ActionResult;
import com.game_diamond.repository.CodeCoinRepo;
import com.game_diamond.service.CodeCoinService;
import com.game_diamond.service.UserCoinService;
import com.game_diamond.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CodeCoinServiceImp implements CodeCoinService {

    @Autowired
    private CodeCoinRepo codeCoinRepo;

    @Autowired
    private UserCoinService userCoinService;

    @Override
    public ActionResult topupCoin(String code, Long userId) {
//        todo: Lay ra code coin do va kiem tra co ton tai hay khong
        Optional<CodeCoin> codeCoinOp = codeCoinRepo.findByCode(code);
        if(codeCoinOp.isEmpty()){
            return new ActionResult(false, null, "Not found code coin");
        }

        CodeCoin codeCoin = codeCoinOp.get();

        if(codeCoin.getIsUsed()){
            return new ActionResult(false, null, "Code coin is used");
        }
//        todo: Neu ton tai thi nap coin cho nguoi dung
        long valueCoin = codeCoin.getValue();
        UserCoin userCoin = userCoinService.createOrUpdate(userId, valueCoin);

        codeCoin.setIsUsed(true);
        codeCoin.setUserId(userId);
        codeCoin.setUpdatesAt(TimeUtils.getCurrentTimestamp());
        codeCoinRepo.save(codeCoin);

//        todo: tao user coin history
        return new ActionResult(true, userCoin, "Success");
    }
}
