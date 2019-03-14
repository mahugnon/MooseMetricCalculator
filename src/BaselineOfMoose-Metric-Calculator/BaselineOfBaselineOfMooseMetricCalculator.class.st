Class {
	#name : #BaselineOfBaselineOfMooseMetricCalculator,
	#superclass : #BaselineOf,
	#category : #'BaselineOfMoose-Metric-Calculator'
}

{ #category : #baselines }
BaselineOfBaselineOfMooseMetricCalculator >> baseline: spec [
	"baselines"

	<baseline>
	spec
		for: #common
		do: [ "Dependencies" self baselineForMenuBar: spec. 
			"Packages "
			spec package: 'Moose-Metric-Calculator-Tests';
			package:'Moose-Metric-Calculator'with: [ spec
						requires:#('MooseVisuOnSpec-MenuBar')]]
]

{ #category : #baselines }
BaselineOfBaselineOfMooseMetricCalculator >> baselineForMenuBar: spec [
	spec
				baseline: 'Moose'
				with: [ spec repository: 'github://mahugnon/VisuProject:bazader/src';
					load:#('MooseVisuOnSpec-MenuBar') ] 
				
]
