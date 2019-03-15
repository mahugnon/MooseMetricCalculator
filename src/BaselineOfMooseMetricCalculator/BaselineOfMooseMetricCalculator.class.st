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
				package: 'Moose-Metric-Calculator'
					with: [ spec requires: #('MooseVisuOnSpec-utilities') ] ]
]

{ #category : #baselines }
BaselineOfMooseMetricCalculator >> baselineForUtilities: spec [
	spec
				baseline: 'Moose'
				with: [ spec loads:#('utilities');
					repository: 'github://mahugnon/VisuProject:bazader/src'
					 ] 
				
]
