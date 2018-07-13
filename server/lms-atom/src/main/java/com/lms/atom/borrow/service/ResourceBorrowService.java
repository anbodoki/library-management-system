package com.lms.atom.borrow.service;

import com.lms.common.dto.atom.resource.ResourceBorrowDTO;
import com.lms.common.dto.response.ListResult;

public interface ResourceBorrowService {

    ResourceBorrowDTO updateResourceBorrow(ResourceBorrowDTO borrow);

    ListResult<ResourceBorrowDTO> getClientResourceBorrow(Long clientId, boolean current, int limit, int offset);

    ListResult<ResourceBorrowDTO> getResourceCopyHistory(String identifier, int limit, int offset);
}
