package dto.service;

import dto.domin.SourceDto;

/**
 * Created by wangyu21 on 2017/2/24.
 */
public interface RpcTestService {

    /**
     *查
     */
    SourceDto getData();

    /**
     *改，删
     */
    boolean update();

    /**
     * 增
     */
    boolean add();
}
