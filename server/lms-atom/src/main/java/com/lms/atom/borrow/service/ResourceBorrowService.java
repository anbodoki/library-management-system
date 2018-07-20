package com.lms.atom.borrow.service;

import com.lms.atom.exception.AtomException;
import com.lms.common.dto.atom.resource.ResourceBorrowDTO;
import com.lms.common.dto.response.ListResult;

import java.util.Date;
import java.util.List;

public interface ResourceBorrowService {

    ResourceBorrowDTO updateResourceBorrow(ResourceBorrowDTO borrow) throws AtomException;

    ListResult<ResourceBorrowDTO> getClientResourceBorrow(Long clientId, boolean current, int limit, int offset);

    ListResult<ResourceBorrowDTO> getResourceCopyHistory(String identifier, int limit, int offset);

    ResourceBorrowDTO get(String bookId, Long clientId) throws Exception;

    List<ResourceBorrowDTO> getLateResourceBorrows();

    List<ResourceBorrowDTO> getTwoDayLeftResourceBorrows();

    ListResult<ResourceBorrowDTO> find(String query, int limit, int offset);

    ListResult<ResourceBorrowDTO> find(String identifier, String isbn, Long clientId,
                                       Date fromBorrowTime, Date toBorrowTime,
                                       Date fromReturnTime, Date toReturnTime,
                                       Date fromScheduledTime, Date toScheduledTime,
                                       Boolean critical, int limit, int offset);
}
