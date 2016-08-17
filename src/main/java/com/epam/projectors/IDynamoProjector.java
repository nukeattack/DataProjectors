package com.epam.projectors;

import com.epam.projectors.entities.MeteoData;
import com.epam.projectors.entities.ProjectionResult;

public interface IDynamoProjector {
  ProjectionResult process(MeteoData data);
}
