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
			self baselineForMenuBar: spec. 
			"Packages "
			spec package: 'Moose-Metric-Calculator-Tests';
			package:'Moose-Metric-Calculator'with: [ spec
						requires:#('MMooseVisuOnSpec-utilities')]]
]

{ #category : #baselines }
BaselineOfMooseMetricCalculator >> baselineForMenuBar: spec [
	spec
				baseline: 'Moose'
				with: [ spec loads:#('MooseVisuOnSpec-utilities');
					repository: 'github://mahugnon/VisuProject:bazader/src'
					 ] 
				
]
