package si.fri.rso.skupina3.rv_review.models.converters;

import si.fri.rso.skupina3.lib.RvReview;
import si.fri.rso.skupina3.rv_review.models.entities.RvReviewEntity;

public class RvReviewConverter {

    public static RvReview toDto(RvReviewEntity entity) {

        RvReview dto = new RvReview();
        dto.setRv_review_id(entity.getRv_review_id());
        dto.setRv_id(entity.getRv_id());
        dto.setUser_id(entity.getUser_id());
        dto.setComment(entity.getComment());
        dto.setScore(entity.getScore());
        dto.setReview_date(entity.getReview_date());

        return dto;
    }

    public static RvReviewEntity toEntity(RvReview dto) {

        RvReviewEntity entity = new RvReviewEntity();
        entity.setRv_review_id(dto.getRv_review_id());
        entity.setRv_id(dto.getRv_id());
        entity.setUser_id(dto.getUser_id());
        entity.setComment(dto.getComment());
        entity.setScore(dto.getScore());
        entity.setReview_date(dto.getReview_date());

        return entity;

    }

}
