Class {
	#name : #MMCMoosePropertyFormPresenter,
	#superclass : #MMCMooseAbstractQueryPresenter,
	#category : #'Moose-Metric-Calculator-Gui-Presenters'
}

{ #category : #examples }
MMCMoosePropertyFormPresenter class >> runMe [
	<example>
	self new openWithSpec
]

{ #category : #accessing }
MMCMoosePropertyFormPresenter >> name [
	^ 'Property'
]
