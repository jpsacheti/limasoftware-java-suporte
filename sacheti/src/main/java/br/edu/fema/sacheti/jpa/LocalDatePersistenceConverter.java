package br.edu.fema.sacheti.jpa;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class LocalDatePersistenceConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate localDate) {
		return localDate == null ? null : Date.valueOf(localDate);
	}

	@Override
	public LocalDate convertToEntityAttribute(Date arg0) {
		return arg0.toLocalDate();
	}

}
