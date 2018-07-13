package com.lms.atom.borrow.storage;

import com.lms.atom.book.storage.ResourceCopyHelper;
import com.lms.atom.borrow.storage.model.ResourceBorrow;
import com.lms.common.dto.atom.resource.ResourceBorrowDTO;

import java.util.ArrayList;
import java.util.List;

public class ResourceBorrowHelper {

    public static ResourceBorrow toEntity(ResourceBorrowDTO borrow) {
        if (borrow == null) {
            return null;
        }
        ResourceBorrow result = new ResourceBorrow();
        result.setId(borrow.getId());
        result.setResourceCopy(ResourceCopyHelper.toEntity(borrow.getResourceCopy()));
        result.setClientId(borrow.getClientId());
        result.setBorrowTime(borrow.getBorrowTime());
        result.setReturnTime(borrow.getReturnTime());
        result.setScheduledReturnTime(borrow.getScheduledReturnTime());
        result.setCritical(borrow.getCritical());
        return result;
    }

    public static ResourceBorrowDTO fromEntity(ResourceBorrow borrow) {
        if (borrow == null) {
            return null;
        }
        ResourceBorrowDTO result = new ResourceBorrowDTO();
        result.setId(borrow.getId());
        result.setResourceCopy(ResourceCopyHelper.fromEntity(borrow.getResourceCopy()));
        result.setClientId(borrow.getClientId());
        result.setBorrowTime(borrow.getBorrowTime());
        result.setReturnTime(borrow.getReturnTime());
        result.setScheduledReturnTime(borrow.getScheduledReturnTime());
        result.setCritical(borrow.getCritical());
        return result;
    }

    public static List<ResourceBorrow> toEntities(List<ResourceBorrowDTO> borrows) {
        if (borrows == null) {
            return null;
        }
        List<ResourceBorrow> result = new ArrayList<>();
        for (ResourceBorrowDTO borrow : borrows) {
            result.add(toEntity(borrow));
        }
        return result;
    }

    public static List<ResourceBorrowDTO> fromEntities(List<ResourceBorrow> borrows) {
        if (borrows == null) {
            return null;
        }
        List<ResourceBorrowDTO> result = new ArrayList<>();
        for (ResourceBorrow borrow : borrows) {
            result.add(fromEntity(borrow));
        }
        return result;
    }
}