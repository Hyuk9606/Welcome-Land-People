package com.ssafy.boonmoja.api.controller.image;

import com.ssafy.boonmoja.api.dto.mapView.MapViewDto;
import com.ssafy.boonmoja.api.entity.image.MapView;
import com.ssafy.boonmoja.api.repository.image.MapViewRepository;
import com.ssafy.boonmoja.oauth.annotation.CurrentUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.ssafy.boonmoja.utils.ApiUtils.ApiResult;
import static com.ssafy.boonmoja.utils.ApiUtils.success;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mapview")
@PreAuthorize("hasAuthority('ROLE_USER')")
public class MapViewController {

    private final MapViewRepository mapViewRepo;

    @Operation(summary = "이미지 or 컬러 중에 타입 선택", description = "현재 로그인된 유저(userId)의 행정구역(loc)의 mapType을 지정한다.")
    @Parameters({
            @Parameter(name = "userId", description = "현재 로그인된 유저"),
            @Parameter(name = "loc", description = "행정구역번호"),
            @Parameter(name = "mapType", description = "image 또는 color 로 값을 요청한다.")
    })
    @PostMapping("/type/{loc}")
    public ApiResult<MapView> postMapType(@CurrentUser String userId,
                                          @PathVariable Integer loc,
                                          @RequestBody MapViewDto mapViewDto) {
        // maptype : image or color
//        log.info("user: {} {}", userId, loc);
        MapView mapView;

        if (mapViewRepo.findByUserAndLoc(userId, loc) == null) {
            mapView = new MapView(loc, mapViewDto.getMapType(), userId);
        } else {
            mapView = mapViewRepo.findByUserAndLoc(userId, loc);
            if (! mapViewDto.getMapType().equals("color")) {
                mapView.setMapType("image");
                mapView.setColor(null);
            } else {
                mapView.setMapType("color");
                mapView.setImage(null);
            }
        }
//        log.info("mapType: {}",mapView.getMapType());
        mapViewRepo.save(mapView);
        return success(mapView);
}

    // 2. 타입이 색깔일 때
    @Operation(summary = "MapView의 색깔을 저장", description = "mapType이 color 일 때 해당 행정구역의 color의 색깔을 저장한다.")
    @Parameters({
            @Parameter(name = "userId", description = "현재 로그인된 유저"),
            @Parameter(name = "loc", description = "행정구역번호"),
            @Parameter(name = "color", description = "저장할 색깔")
    })
    @PostMapping("/color/{loc}")
    public ApiResult<MapView> postViewColor(@CurrentUser String userId,
                                       @PathVariable Integer loc,
                                       @RequestBody MapViewDto mapViewDto) throws Exception {
        if(!mapViewRepo.findByUserAndLoc(userId, loc).getMapType().equals("color")) throw new Exception("It's not color. 잘못된 요청 입니다.");
        MapView mapView = mapViewRepo.findByUserAndLoc(userId, loc);
        mapView.setColor(mapViewDto.getColor());
        mapViewRepo.save(mapView);
        return success(mapView);
    }

    // 2. 타입이 이미지일 때 - 한장만
    @Operation(summary = "MapView에 사진을 저장", description = "mapType이 image 일 때 해당 행정구역에 사진을 저장한다.")
    @Parameters({
            @Parameter(name = "userId", description = "현재 로그인된 유저"),
            @Parameter(name = "loc", description = "행정구역번호"),
            @Parameter(name = "image", description = "저장할 사진")
    })
    @PostMapping("/image/{loc}")
    public ApiResult<MapView> postViewImage(@CurrentUser String userId,
                                       @PathVariable Integer loc,
                                       @RequestParam("image") MultipartFile image) throws Exception {
        if(!mapViewRepo.findByUserAndLoc(userId, loc).getMapType().equals("image")) throw new Exception("It's not an image. 잘못된 요청 입니다.");
        MapView mapView = mapViewRepo.findByUserAndLoc(userId, loc);
        mapView.setImage(new Binary(BsonBinarySubType.BINARY, image.getBytes()));
        mapViewRepo.save(mapView);

        return success(mapView);
    }

    @Operation(summary = "행정구역의 MapView 정보 조회", description = "현재 로그인한 유저의 해당 행정구역의 MapViewInfo(type,color,image)를 조회한다.")
    @Parameters({
            @Parameter(name = "userId", description = "현재 로그인된 유저"),
            @Parameter(name = "loc", description = "행정구역번호"),
    })
    @GetMapping("/info/{loc}")
    public ApiResult<MapView> getMapInfo(@CurrentUser String userId, @PathVariable Integer loc) {
        MapView mapView = mapViewRepo.findByUserAndLoc(userId, loc);
        return success(mapView);
    }

    @Operation(summary = "행정구역의 MapView 삭제", description = "현재 로그인한 유저의 해당 행정구역의 MapViewInfo(type,color,image)를 삭제한다.")
    @Parameters({
            @Parameter(name = "userId", description = "현재 로그인된 유저"),
            @Parameter(name = "loc", description = "행정구역번호"),
    })
    @DeleteMapping("/{loc}")
    public ApiResult<MapView> deleteMapView(@CurrentUser String userId, @PathVariable Integer loc) {
        mapViewRepo.deleteByUserAndLoc(userId, loc);
        MapView mapView = mapViewRepo.findByUserAndLoc(userId, loc);
        return success(mapView);
    }
}
