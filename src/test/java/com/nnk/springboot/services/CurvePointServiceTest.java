package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.CurvePoint;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurvePointServiceTest {

	@Autowired
	private CurvePointService curvePointService;

	@Test
	public void curvePointTest() {
		CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);
		// Save
		curvePoint = curvePointService.saveCurvePoint(curvePoint);
		Assert.assertNotNull(curvePoint.getId());
		Assert.assertTrue(curvePoint.getCurveId() == 10);

		// Update
		curvePoint.setCurveId(20);
		curvePoint = curvePointService.saveCurvePoint(curvePoint);
		Assert.assertTrue(curvePoint.getCurveId() == 20);

		// Find
		List<CurvePoint> listResult = (List<CurvePoint>) curvePointService
				.getCurvePoints();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = curvePoint.getId();
		curvePointService.deleteCurvePoint(curvePoint);
		Optional<CurvePoint> curvePointList = curvePointService
				.getCurvePointById(id);
		Assert.assertFalse(curvePointList.isPresent());
	}

}
