package com.nnk.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@Service
public class CurvePointService {

	@Autowired
	private CurvePointRepository curvePointRepository;

	public Iterable<CurvePoint> getCurvePoints() {
		return curvePointRepository.findAll();
	}

	public Optional<CurvePoint> getCurvePointById(Integer id) {
		return curvePointRepository.findById(id);
	}

	public CurvePoint saveCurvePoint(CurvePoint curvePoint) {
		return curvePointRepository.save(curvePoint);
	}

	public Boolean deleteCurvePoint(CurvePoint curve) {
		curvePointRepository.delete(curve);
		return true;
	}

}
