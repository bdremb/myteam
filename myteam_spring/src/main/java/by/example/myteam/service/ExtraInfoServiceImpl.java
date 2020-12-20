package by.example.myteam.service;

import by.example.myteam.dao.ExtraInfoDAO;
import by.example.myteam.entity.ExtraInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExtraInfoServiceImpl implements ExtraInfoService {

    private ExtraInfoDAO extraInfoDAO;

    @Autowired
    public ExtraInfoServiceImpl(ExtraInfoDAO extraInfoDAO) {
        this.extraInfoDAO = extraInfoDAO;
    }

    @Override
    @Transactional
    public void deleteExtraInfoById(long id) {

    }

    @Override
    @Transactional
    public boolean saveExtraInfo(ExtraInfo extraInfo) {
        extraInfoDAO.saveExtraInfo(extraInfo);
        return true;
    }

    @Override
    @Transactional
    public ExtraInfo getExtraInfo(int id) {
        return null;
    }
}
