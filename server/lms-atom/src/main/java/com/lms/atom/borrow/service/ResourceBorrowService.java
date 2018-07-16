package com.lms.atom.borrow.service;

import com.lms.atom.exception.AtomException;
import com.lms.common.dto.atom.resource.ResourceBorrowDTO;
import com.lms.common.dto.response.ListResult;

public interface ResourceBorrowService {

    ResourceBorrowDTO updateResourceBorrow(ResourceBorrowDTO borrow) throws AtomException;

    ListResult<ResourceBorrowDTO> getClientResourceBorrow(Long clientId, boolean current, int limit, int offset);

    ListResult<ResourceBorrowDTO> getResourceCopyHistory(String identifier, int limit, int offset);

    ResourceBorrowDTO get(String bookId, Long clientId) throws Exception;
}
