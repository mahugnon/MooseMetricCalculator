Class {
	#name : #BaselineOfMooseMetricCalculator,
	#superclass : #BaselineOf,
	#category : #BaselineOfMooseMetricCalculator
}

{ #category : #baselines }
BaselineOfMooseMetricCalculator >> baseline: spec [
	"baselines"

	<baseline>
	spec
		for: #common
		do: [ "Dependencies"
			self baselineForUtilities: spec.
			"Packages "
			spec
				package: 'Moose-Metric-Calculator-Tests';
				package: 'Moose-Metric-Calculator']
]

{ #category : #baselines }
BaselineOfMooseMetricCalculator >> baselineForUtilities: spec [
	spec
		baseline: 'MooseVisuProject'
		with:
			[ spec repository: 'github://mahugnon/MooseVisuOnSpec:master/src' ]
]
